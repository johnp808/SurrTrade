export class userDTO {
  id: number;
  username: string;
  email: string;
  primaryBike: string;
  status: string;
  role: string;
  createdAt: Date;
  updatedAt: Date;
  lastLogin: Date;
  bikePicture: string;
  userPicture: string;
  enabled: boolean;

  constructor(
    id: number = 0,
    username: string = '',
    email: string = '',
    primaryBike: string = '',
    status: string = '',
    role: string = '',
    createdAt: Date = new Date(),
    updatedAt: Date = new Date(),
    lastLogin: Date = new Date(),
    bikePicture: string = '',
    userPicture: string = '',
    enabled: boolean = true
  ) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.primaryBike = primaryBike;
    this.status = status;
    this.role = role;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.lastLogin = lastLogin;
    this.bikePicture = bikePicture;
    this.userPicture = userPicture;
    this.enabled = enabled;
  }
}
