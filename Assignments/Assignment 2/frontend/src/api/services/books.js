import authHeader, { BASE_URL, HTTP } from "../http";

export default {

  create(book) {
    return HTTP.post(BASE_URL + "/book", book, { headers: authHeader() }).then(
        (response) => {
          return response.data;
        }
    );
  },
  allBooks() {
    return HTTP.get(BASE_URL + "/book", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  edit(book) {
    return HTTP.patch(BASE_URL + "/book", book, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  deleteById(id){
    return HTTP.delete(BASE_URL + "/book/"+ id , {headers: authHeader()}).then(
        () => {
          return true;
        }
    );
  },
  search(description){
    return HTTP.get(BASE_URL + "/book/search/" + description,{headers: authHeader()}).then(
        (response) => {
          return response.data;
        }
    );
  },
  sell(book, amount){
    return HTTP.patch(BASE_URL + "/book/sell/" + amount, book,{headers: authHeader()}).then(
        (response) => {
          return response.data;
        }
    );
  }

  //TODO: csv & pdf
};
