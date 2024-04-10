package com.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;

import com.user.dto.EducationDTO;
import com.user.dto.LoginDTO;
import com.user.dto.SummaryDTO;
import com.user.dto.UserDTO;
import com.user.entity.SequenceId;
import com.user.entity.User;
import com.user.exception.UserException;
import com.user.exception.UserNotFoundException;
import com.user.repository.UserRepository;
import com.user.service.UserService;
import com.user.service.UserServiceImpl;
import com.user.utility.RestUtility;
import com.user.utility.Transformations;

@SpringBootTest
class UserMsApplicationTests {

	@Mock
	private UserRepository repository;
	
	@Mock
	private MongoOperations mongoOperation;
	
	@Mock
	RestUtility restUtil;
	

	@InjectMocks
	private UserService userService = new UserServiceImpl();

	@Test
	void testFindUserByEmailId_ValidEmail_ReturnsUser() throws UserNotFoundException {
		// Arrange
		
		String validEmail = "ramesh@infosys.com";
		User mockUser = new User();
		mockUser.setEmail(validEmail);
		mockUser.setFirstName("Vishal");
		mockUser.setId(1L);
		Mockito.when(repository.findByEmail(validEmail)).thenReturn(Optional.of(mockUser));

		// Act
		User result = userService.findUserByEmailId(validEmail);

		// Assert

		Assertions.assertNotNull(result);
		Assertions.assertEquals(1L, result.getId());
		Assertions.assertEquals("Vishal", result.getFirstName());
		Assertions.assertEquals(validEmail, result.getEmail());
	}

	@Test
	void testFindUserByEmailId_InvalidEmail_ThrowsUserNotFoundException() {
		// Arrange
		String invalidEmail = "invalid@infosys.com";
		Mockito.when(repository.findByEmail(invalidEmail)).thenReturn(Optional.empty());

		// Act and Assert
		UserNotFoundException e = Assertions.assertThrows(UserNotFoundException.class, () -> {
			userService.findUserByEmailId(invalidEmail);
		});

		Assertions.assertEquals("Service.USERS_NOT_FOUND", e.getMessage());
	}

	@Test
	void testRegister_ValidEmail_ReturnsUserDTO() throws UserException {
		// Arrange
		String validEmail = "test@example.com";
		UserDTO mockDto = new UserDTO();
		mockDto.setEmail(validEmail);
		mockDto.setId(1l);
		mockDto.setImage("assets\\uploadedImages\\default.jpg");
		EducationDTO edu = new EducationDTO();
		edu.setDegree("");
		edu.setInstitution("");
		edu.setFieldOfStudy("");
		SummaryDTO sum = new SummaryDTO();
		sum.setAspiration("");
		sum.setExperience("");
		sum.setSkill("");
		mockDto.setEducation(edu);
		mockDto.setSummary(sum);
		User ent = Transformations.userDtoToEntity(mockDto);
		SequenceId seq = new SequenceId();
		seq.setSeq(1l);
		
		Mockito.when(repository.findByEmail(validEmail)).thenReturn(Optional.empty());
		Mockito.when(repository.save(any(User.class))).thenReturn(ent);
		Mockito.when(mongoOperation.findAndModify(any(Query.class), any(Update.class), any(FindAndModifyOptions.class),
				eq(SequenceId.class))).thenReturn(seq);
		doNothing().when(restUtil).createConnection(anyString());

		// Act
		UserDTO result = userService.register(mockDto);

		// Assert
		Assertions.assertNotNull(result);
		Assertions.assertEquals(1L, result.getId());
		Assertions.assertEquals(validEmail, result.getEmail());
		Assertions.assertEquals("assets\\uploadedImages\\default.jpg", result.getImage());
		Assertions.assertEquals("", result.getEducation().getDegree());
		Assertions.assertEquals("", result.getSummary().getAspiration());
	}

	@Test
	void testRegister_DuplicateEmail_ThrowsUserException() {
		// Arrange
		String duplicateEmail = "duplicate@example.com";
		UserDTO mockDto = new UserDTO();
		mockDto.setEmail(duplicateEmail);
		Mockito.when(repository.findByEmail(duplicateEmail)).thenReturn(Optional.of(new User()));

		// Act and Assert
		Assertions.assertThrows(UserException.class, () -> {
			userService.register(mockDto);
		});
	}

	@Test
	void testValidateUser_ValidCredentials_ReturnsUserDTO() throws UserException, UserNotFoundException {
		// Arrange
		String validEmail = "test@example.com";
		String validPassword = "securePassword";
		User mockUser = new User();
		mockUser.setEmail(validEmail);
		mockUser.setPassword(validPassword);
		Mockito.when(repository.findByEmail(validEmail)).thenReturn(Optional.of(mockUser));

		// Act
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail(validEmail);
		loginDTO.setPassword(validPassword);
		UserDTO result = userService.validateUser(loginDTO);

		// Assert
		Assertions.assertNotNull(result);
		Assertions.assertEquals(validEmail, result.getEmail());
	}

	@Test
	void testValidateUser_InvalidEmail_ThrowsUserNotFoundException() {
		// Arrange
		String invalidEmail = "invalid@example.com";
		Mockito.when(repository.findByEmail(invalidEmail)).thenReturn(Optional.empty());

		// Act and Assert
		Assertions.assertThrows(UserNotFoundException.class, () -> {
			LoginDTO loginDTO = new LoginDTO();
			loginDTO.setEmail(invalidEmail);
			loginDTO.setPassword("Password");
			userService.validateUser(loginDTO);
		});
	}

//
	@Test
	void testValidateUser_IncorrectPassword_ThrowsUserException() {
		// Arrange
		String validEmail = "test@example.com";
		String validPassword = "securePassword";
		User mockUser = new User();
		mockUser.setEmail(validEmail);
		mockUser.setPassword("differentPassword"); // Incorrect password
		Mockito.when(repository.findByEmail(validEmail)).thenReturn(Optional.of(mockUser));

		// Act and Assert
		Assertions.assertThrows(UserException.class, () -> {
			LoginDTO loginDTO = new LoginDTO();
			loginDTO.setEmail(validEmail);
			loginDTO.setPassword(validPassword);
			userService.validateUser(loginDTO);
		});
	}

	@Test
	void testResetPassword_ValidPhoneNumber_ReturnsUserDTO() throws UserException, UserNotFoundException {
		// Arrange
		String validEmail = "test@example.com";
		String validPhone = "1234567890";
		String newPassword = "newSecurePassword";
		User mockUser = new User();
		mockUser.setEmail(validEmail);
		mockUser.setPhoneNo(validPhone);
		mockUser.setPassword("existingPassword"); // Existing password
		Mockito.when(repository.findByEmail(validEmail)).thenReturn(Optional.of(mockUser));
		Mockito.when(repository.save(any(User.class))).thenReturn(mockUser);

		// Act
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail(validEmail);
		loginDTO.setPassword(newPassword);
		loginDTO.setPhoneNo(validPhone);
		UserDTO result = userService.resetPassword(loginDTO);

		// Assert
		Assertions.assertNotNull(result);
		Assertions.assertEquals(validEmail, result.getEmail());
		Assertions.assertEquals(newPassword, mockUser.getPassword());
	}

	@Test
	void testResetPassword_InvalidPhoneNumber_ThrowsUserException() {
		// Arrange
		String validEmail = "test@example.com";
		String validPhone = "1234567890";
		String newPassword = "newSecurePassword";
		User mockUser = new User();
		mockUser.setEmail(validEmail);
		mockUser.setPhoneNo("differentPhoneNumber"); // Incorrect phone number
		Mockito.when(repository.findByEmail(validEmail)).thenReturn(Optional.of(mockUser));

		// Act and Assert
		Assertions.assertThrows(UserException.class, () -> {
			LoginDTO loginDTO = new LoginDTO();
			loginDTO.setEmail(validEmail);
			loginDTO.setPassword(newPassword);
			loginDTO.setPhoneNo(validPhone);
			userService.resetPassword(loginDTO);
		});
	}

	@Test
	void testProfileCreate_ValidUserUpdate_ReturnsUpdatedUserDTO() throws UserNotFoundException, UserException {
		// Arrange
		String validUserId = "test@example.com";
		User mockUser = new User();
		mockUser.setEmail(validUserId);
		mockUser.setImage("existingImage.jpg");
		mockUser.setId(1L);
		Mockito.when(repository.findByEmail(validUserId)).thenReturn(Optional.of(mockUser));
		Mockito.when(repository.save(any(User.class))).thenReturn(mockUser);

		// Act
		UserDTO updatedUserDTO = new UserDTO();
		updatedUserDTO.setEmail(validUserId);
		updatedUserDTO.setImage("existingImage.jpg");

		UserDTO result = userService.profileCreate(validUserId, updatedUserDTO);

		// Assert
		Assertions.assertNotNull(result);
		Assertions.assertEquals(validUserId, result.getEmail());
		Assertions.assertEquals("existingImage.jpg", result.getImage());
		Assertions.assertEquals(1L, result.getId());
	}

	@Test
	void testProfileCreate_InvalidUser_ThrowsUserNotFoundException() {
		// Arrange
		String invalidUserId = "invalid@example.com";
		Mockito.when(repository.findByEmail(invalidUserId)).thenReturn(Optional.empty());

		// Act and Assert
		Assertions.assertThrows(UserNotFoundException.class, () -> {
			UserDTO updatedUserDTO = new UserDTO();
			updatedUserDTO.setEmail(invalidUserId);
			userService.profileCreate(invalidUserId, updatedUserDTO);
		});
	}

	@Test
	void testGetUserProfile_ValidUser_ReturnsUserDTO() throws UserNotFoundException, UserException {
		// Arrange
		String validEmail = "test@example.com";
		User mockUser = new User();
		mockUser.setEmail(validEmail);
		mockUser.setImage("existingImage.jpg");
		mockUser.setId(1L);
		Mockito.when(repository.findByEmail(validEmail)).thenReturn(Optional.of(mockUser));

		// Act
		UserDTO result = userService.getUserProfile(validEmail);

		// Assert
		Assertions.assertNotNull(result);
		Assertions.assertEquals(validEmail, result.getEmail());
		Assertions.assertEquals("existingImage.jpg", result.getImage());
		Assertions.assertEquals(1L, result.getId());
	}

	@Test
	void testGetUserProfile_InvalidUser_ThrowsUserNotFoundException() {
		// Arrange
		String invalidEmail = "invalid@example.com";
		Mockito.when(repository.findByEmail(invalidEmail)).thenReturn(Optional.empty());

		// Act and Assert
		Assertions.assertThrows(UserNotFoundException.class, () -> {
			userService.getUserProfile(invalidEmail);
		});
	}

	@Test
	void testGetAllUsers_ValidUsers_ReturnsUserDTOList() {
		// Arrange
		User mockUser1 = new User();
		mockUser1.setEmail("user1@example.com");
		mockUser1.setImage("image1.jpg");
		mockUser1.setId(1L);

		User mockUser2 = new User();
		mockUser2.setEmail("user2@example.com");
		mockUser2.setImage("image2.jpg");
		mockUser2.setId(2L);

		Mockito.when(repository.findAll()).thenReturn(List.of(mockUser1, mockUser2));

		// Act
		List<UserDTO> result = userService.getAllUsers();

		// Assert
		Assertions.assertNotNull(result);
		Assertions.assertEquals(2, result.size());

		UserDTO user1DTO = result.get(0);
		Assertions.assertEquals("user1@example.com", user1DTO.getEmail());
		Assertions.assertEquals("image1.jpg", user1DTO.getImage());
		Assertions.assertEquals(1L, user1DTO.getId());

		UserDTO user2DTO = result.get(1);
		Assertions.assertEquals("user2@example.com", user2DTO.getEmail());
		Assertions.assertEquals("image2.jpg", user2DTO.getImage());
		Assertions.assertEquals(2L, user2DTO.getId());
	}

	@Test
	void testGetAllUsers_EmptyUsers_ReturnsEmptyList() {
		// Arrange
		Mockito.when(repository.findAll()).thenReturn(Collections.emptyList());

		// Act
		List<UserDTO> result = userService.getAllUsers();

		// Assert
		Assertions.assertNotNull(result);
		Assertions.assertTrue(result.isEmpty());
	}
}

