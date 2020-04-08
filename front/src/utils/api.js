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
        dealErrorRedirect(e);
      }
      if (errorMessage) {
        dealErrorDialog(e);
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
          dealErrorRedirect(e);
        }
        if (errorMessage) {
          dealErrorDialog(e);
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
          dealErrorRedirect(e);
        }
        if (errorMessage) {
          dealErrorDialog(e);
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
          dealErrorRedirect(e);
        }
        if (errorMessage) {
          dealErrorDialog(e);
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
          dealErrorRedirect(e);
        }
        if (errorMessage) {
          dealErrorDialog(e);
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
          dealErrorRedirect(e);
        }
        if (errorMessage) {
          dealErrorDialog(e);
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

/**
 * Deal with the redirection of error
 * @param {AxiosError} error
 */
function dealErrorRedirect(error) {
  if (error.response) {
    /*
     * The request was made and the server responded with a
     * status code that falls out of the range of 2xx
     */
    router.push({ name: `error_${error.response.status}` });
  } else if (error.request) {
    /*
     * The request was made but no response was received, `error.request`
     * is an instance of XMLHttpRequest in the browser and an instance
     * of http.ClientRequest in Node.js
     */
    router.push({ name: "error" });
  } else {
    // Something happened in setting up the request and triggered an Error
    router.push({ name: "error" });
  }
}

/**
 * Deal with the redirection of dialog
 * @param {AxiosError} error
 */
function dealErrorDialog(error) {
  if (error.response) {
    /*
     * The request was made and the server responded with a
     * status code that falls out of the range of 2xx
     */
    store.dispatch("core/setMessageError", error.response.status);
  } else if (error.request) {
    /*
     * The request was made but no response was received, `error.request`
     * is an instance of XMLHttpRequest in the browser and an instance
     * of http.ClientRequest in Node.js
     */
    store.dispatch("core/setMessageError", "");
  } else {
    // Something happened in setting up the request and triggered an Error
    store.dispatch("core/setMessageError", "");
  }
}
