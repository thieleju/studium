<template>
  <div>
    <h1>StartView</h1>
    <v-btn @click="login">Click here to login</v-btn>
    <v-btn>Click here to register</v-btn>
  </div>
</template>

<script>
export default {
  methods: {
    login() {
      this.$store.dispatch("login").then(() => {
        this.$router.push({ name: "home" });
      });
    },
  },
  created() {
    if (this.$store.getters.isAuthenticated) {
      this.$router.push({ name: "home" });
    }
  },
};
</script>

<style></style>
