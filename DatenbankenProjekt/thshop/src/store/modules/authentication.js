import axios from "@/plugins/axios";

export default {
  state: {
    isAuthenticated: false,
    user: null,
    apps: [],
  },
  mutations: {
    SET_AUTHENTICATED(state, isAuthenticated) {
      state.isAuthenticated = isAuthenticated;
    },
    SET_USER(state, user) {
      state.user = user;
    },
    SET_APPS(state, apps) {
      state.apps = apps;
    },
  },
  actions: {
    async login({ commit }) {
      const loginRes = await axios.get("/auth/login");

      console.log(loginRes.data);
      const apps = [
        {
          title: "Home",
          name: "home",
          icon: "mdi-home",
        },
        {
          title: "Profile",
          name: "profile",
          icon: "mdi-account",
        },
        {
          title: "About",
          name: "about",
          icon: "mdi-information",
        },
      ];
      commit("SET_APPS", apps);
      commit("SET_AUTHENTICATED", true);
    },
    logout({ commit }) {
      commit("SET_AUTHENTICATED", false);
      commit("SET_USER", null);
      commit("SET_APPS", []);
    },
    setApps({ commit }, apps) {
      commit("SET_APPS", apps);
    },
  },
  getters: {
    isAuthenticated(state) {
      return state.isAuthenticated;
    },
    getUser(state) {
      return state.user;
    },
    getApps(state) {
      return state.apps;
    },
  },
};
