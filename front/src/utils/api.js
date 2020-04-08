import axios from "axios";
import router from "@/router";
import store from "@/store";

const route = "http://localhost:8000";

export default {
  postNoAuth(
    endpoint,
    params,
    { errorRedirect, errorMessage } = {
      errorRedirect: false,
      errorMessage: false
    }
  ) {
    return axios.post(`${route}/${endpoint}`, params).catch(e => {
      if (errorRedirect) {
        router.push({ name: "error" });
      }
      if (errorMessage) {
        store.dispatch("core/setMessageError");
      }
      return Promise.reject(e);
    });
  },
  get(
    endpoint,
    params,
    { errorRedirect, errorMessage } = {
      errorRedirect: false,
      errorMessage: false
    }
  ) {
    return axios
      .get(`${route}/${endpoint}`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
          "Content-Type": "application/json"
        },
        params
      })
      .catch(e => {
        if (errorRedirect) {
          router.push({ name: "error" });
        }
        if (errorMessage) {
          store.dispatch("core/setMessageError");
        }
        return Promise.reject(e);
      });
  },
  post(
    endpoint,
    params,
    { errorRedirect, errorMessage } = {
      errorRedirect: false,
      errorMessage: false
    }
  ) {
    return axios
      .post(`${route}/${endpoint}`, params, { headers: setToken() })
      .catch(e => {
        if (errorRedirect) {
          router.push({ name: "error" });
        }
        if (errorMessage) {
          store.dispatch("core/setMessageError");
        }
        return Promise.reject(e);
      });
  },
  put(
    endpoint,
    params,
    { errorRedirect, errorMessage } = {
      errorRedirect: false,
      errorMessage: false
    }
  ) {
    return axios
      .put(`${route}/${endpoint}`, params, { headers: setToken() })
      .catch(e => {
        if (errorRedirect) {
          router.push({ name: "error" });
        }
        if (errorMessage) {
          store.dispatch("core/setMessageError");
        }
        return Promise.reject(e);
      });
  },
  patch(
    endpoint,
    params,
    { errorRedirect, errorMessage } = {
      errorRedirect: false,
      errorMessage: false
    }
  ) {
    return axios
      .put(`${route}/${endpoint}`, params, { headers: setToken() })
      .catch(e => {
        if (errorRedirect) {
          router.push({ name: "error" });
        }
        if (errorMessage) {
          store.dispatch("core/setMessageError");
        }
        return Promise.reject(e);
      });
  },
  delete(
    endpoint,
    params,
    { errorRedirect, errorMessage } = {
      errorRedirect: false,
      errorMessage: false
    }
  ) {
    return axios
      .delete(
        `${route}/${endpoint}`,
        Object.assign({ headers: setToken() }, { params: params })
      )
      .catch(e => {
        if (errorRedirect) {
          router.push({ name: "error" });
        }
        if (errorMessage) {
          store.dispatch("core/setMessageError");
        }
        return Promise.reject(e);
      });
  }
};

function setToken() {
  return {
    Authorization: `Bearer ${localStorage.getItem("token")}`
  };
}
