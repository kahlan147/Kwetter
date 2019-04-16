import {User} from "./user";

export class Post {

  public id: bigint;
  public message: String;
  public date: Date;
  public isReaction: boolean;
  public poster: User;
  public reactions: Post[];

  constructor(){

  }
}
