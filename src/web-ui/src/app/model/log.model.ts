export enum LogType {
  AUTH= "AUTH",
  UFW = "UFW",
  DPKG = "DPKG",
  KERN = "KERN",
  FAIL2BAN = "FAIL2BAN",
  BOOTSTRAP = "BOOTSTRAP",
  APPORT = "APPORT",
  ALTERNATIVES = "ALTERNATIVES",

  APACHE2_ACCESS = "APACHE2_ACCESS",
  APACHE2_ERROR = "APACHE2_ERROR",
  APACHE2_OTHER_VHOSTS_ACCESS = "APACHE2_OTHER_VHOSTS_ACCESS"
}

export interface AvailableLogTypesResponse {
  availableLogTypes: LogType[];
}

export interface LogsRequest {
  logType: LogType;
  startLine: number;
  lines: number;
}

export interface LogsResponse {
  totalLines: number;
  startLine: number;
  lines: string[];
}
