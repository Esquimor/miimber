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
    return axios.post(`${route}/${endpoint}`, params, setToken());
  },
  put(endpoint, params) {
    return axios.put(`${route}/${endpoint}`, params, setToken());
  },
  delete(endpoint, params) {
    return axios.delete(
      `${route}/${endpoint}`,
      Object.assign(setToken(), { params: params })
    );
  }
};

function setToken() {
  return {
    Authorization: `Bearer ${localStorage.getItem("token")}`
  };
}
