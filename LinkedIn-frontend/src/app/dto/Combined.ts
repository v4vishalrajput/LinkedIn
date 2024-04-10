import { Post } from "./Post";
import { User } from "./user";

export class Combined {
    postList: Post[];
    userList: User[];

    constructor(pList: Post[], uList: User[]) {
        this.postList = pList;
        this.userList = uList;
    }
}