import axios from "axios";

const route = "http://localhost:8000";

export default {
  postNoAuth(endpoint, params) {
    return axios.post(`${route}/${endpoint}`, params);
  },
  get(endpoint, params) {
    return axios.get(`${route}/${endpoint}`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
        "Content-Type": "application/json"
      },
      params
    });
  },
  post(endpoint, params) {
    return axios.post(`${route}/${endpoint}`, params, { headers: setToken() });
  },
  put(endpoint, params) {
    return axios.put(`${route}/${endpoint}`, params, { headers: setToken() });
  },
  delete(endpoint, params) {
    return axios.delete(
      `${route}/${endpoint}`,
      Object.assign({ headers: setToken() }, { params: params })
    );
  }
};

function setToken() {
  return {
    Authorization: `Bearer ${localStorage.getItem("token")}`
  };
}
