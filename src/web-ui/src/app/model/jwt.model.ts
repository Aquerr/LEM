import {Authority} from "./authority.model";

export interface LoginRequest {
  username: string;
  password: string;
}

export interface JwtTokenResponse {
  jwt: string;
  username: string;
  authorities: Authority[];
}
