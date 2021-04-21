import authHeader, { BASE_URL, HTTP } from "../http";

export default {

      create(bookDTO) {
        return HTTP.post(BASE_URL + "/books", bookDTO, { headers: authHeader() }).then(
            (response) => {
              return response.data;
            }
        );
      },

      findAll() {
        return HTTP.get(BASE_URL + "/books", { headers: authHeader() }).then(
          (response) => {
            return response.data;
          }
        );
      },

      getBook(id){
          return HTTP.get(BASE_URL + "/books/" + id, { headers: authHeader() }).then(
              (response) => {
                  return response.data;
              }
          );
      },

      edit(id, bookDTO) {
        return HTTP.put(BASE_URL + "/books/" + id, bookDTO, { headers: authHeader() }).then(
          (response) => {
            return response.data;
          }
        );
      },

      changeAmount(id, amount) {
        return HTTP.patch(BASE_URL + "/books/" + id, amount, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
      },

      deleteById(id){
        return HTTP.delete(BASE_URL + "/books/" + id , {headers: authHeader()}).then(
            () => {
              return true;
            }
        );
      },

      deleteAll(){
        return HTTP.delete(BASE_URL + "/books", {headers: authHeader()}).then(
            () => {
              return true;
            }
        );
      },

    filterByDescription(description){
        return HTTP.get(BASE_URL + "/books/" + description,{headers: authHeader()}).then(
            (response) => {
              return response.data;
            }
        );
      },

    findOutOfStock(){
            return HTTP.get(BASE_URL + "/books/out_stock",{headers: authHeader()}).then(
                (response) => {
                    return response.data;
                }
            );
      },

    // REPORTS
    PDF() {
        console.log("PDF");
        return HTTP.get(BASE_URL + "/export/PDF", {
            headers: authHeader(),
        }).then((response) => {
            return response.data;
        });
    },

    CSV() {
        console.log("CSV");
        return HTTP.get(BASE_URL + "/export/CSV", {
            headers: authHeader(),
        }).then((response) => {
            return response.data;
        });
    },
};
