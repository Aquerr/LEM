var PROXY_CONFIG = {
  "/api/*": {
    "target": "http://localhost:8086",
    "secure": false,
    "logLevel": "debug",
    "changeOrigin": true
  }
}

module.exports = PROXY_CONFIG;
