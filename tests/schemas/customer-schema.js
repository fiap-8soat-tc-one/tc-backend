export const createCustomerResponseSchema = {
    "properties": {
      "id": {
        "type": "string"
      },
      "document": {
        "type": "string"
      },
      "name": {
        "type": "string"
      },
      "email": {
        "type": "string"
      }                                         
    }
  };


  export const getCustomerResponseSchema = {
    "properties": {
      "id": {
        "type": "string"
      },
      "document": {
        "type": "string"
      },
      "name": {
        "type": "string"
      },
      "email": {
        "type": "string"
      }                                         
    }
  };


  export const listOfCustomerResponseSchema = {
    "properties": {
      "content": {
        "type": "array"
      },
      "pageable": {
        "type": "object"
      },
      "total_pages": {
        "type": "number"
      },
      "total_elements": {
        "type": "number"
      },
      "last": {
        "type": "boolean"
      },
      "size": {
        "type": "number"
      },
      "number": {
        "type": "number"
      },
      "sort": {
        "type": "object"
      },
      "number_of_elements": {
        "type": "number"
      },
      "first": {
        "type": "boolean"
      },
      "empty": {
        "type": "boolean"
      }                                              
    }
  };