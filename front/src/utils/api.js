import axios from "axios";

const route = "http://localhost:8000";

export default {
  get(endpoint, params) {
    return axios.get(`${route}/papi/${endpoint}`, params);
  },
  post(endpoint, params) {
    return axios.post(`${route}/papi/${endpoint}`, params);
  },
  put(endpoint, params) {
    return axios.put(`${route}/papi/${endpoint}`, params);
  },
  delete(endpoint, params) {
    return axios.delete(`${route}/papi/${endpoint}`, { params: params });
  }
};
