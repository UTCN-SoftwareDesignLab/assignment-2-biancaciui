import authHeader, { BASE_URL, HTTP } from "../http";

export default {

      sell(id, amount){
        return HTTP.post(BASE_URL + "/bookstore/"+ id + "/" + amount,{headers: authHeader()}).then(
            (response) => {
              return response.data;
            }
        );
      },
    filterByDescription(description){
        return HTTP.get(BASE_URL + "/bookstore/" + description,{headers: authHeader()}).then(
            (response) => {
                return response.data;
            }
        );
    },

};
