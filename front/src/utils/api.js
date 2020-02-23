import axios from "axios";

const route = "http://localhost:8000";

export default {
  postNoAuth(endpoint, params) {
    return axios.post(`${route}/${endpoint}`, params);
  },
  get(endpoint, params) {
    return axios.get(`${route}/${endpoint}`, params);
  },
  post(endpoint, params) {
    return axios.post(`${route}/${endpoint}`, params);
  },
  put(endpoint, params) {
    return axios.put(`${route}/${endpoint}`, params);
  },
  delete(endpoint, params) {
    return axios.delete(`${route}/${endpoint}`, { params: params });
  }
};
