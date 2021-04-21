import authHeader, { BASE_URL, HTTP } from "../http";
export default {
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