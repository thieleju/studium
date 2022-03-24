import axios from "axios";

const api = axios.create({
  baseURL: String(process.env.VUE_APP_BASE_URL),
});

api.interceptors.request.use((config) => {
  const token = localStorage.getItem("jwt.token");

  if (config && config.headers && token !== null)
    config.headers.Authorization = "Bearer " + token;

  return config;
});

api.interceptors.response.use(
  (success) => {
    return success;
  },
  (failure) => {
    return Promise.reject(failure);
  }
);

export default api;
