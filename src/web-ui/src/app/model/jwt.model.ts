export interface LoginReuqest {
  username: string;
  password: string;
}

export interface JwtTokenResponse {
  jwt: string;
  username: string;
  authorities: string[];
}
