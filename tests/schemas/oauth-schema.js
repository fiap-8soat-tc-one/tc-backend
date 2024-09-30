export const oAuthResponseSchema = {
    "properties": {
      "access_token": {
        "type": "string"
      },
      "token_type": {
        "type": "string"
      },
      "expires_in": {
        "type": "number"
      },
      "scope": {
        "type": "string"
      },
      "profile": {
        "type": "string"
      },
      "name": {
        "type": "string"
      },
      "uuid": {
        "type": "string"
      },
      "jti": {
        "type": "string"
      },
    }
  };
  