<template>
  <v-app id="inspire">
    <v-app-bar app color="white" flat>
      <v-container class="py-0 fill-height">
        <v-avatar class="mr-10" color="grey darken-1" size="32"></v-avatar>

        <v-btn
          v-for="(app, i) in apps"
          :key="i"
          :to="{ name: app.name }"
          depressed
          plain
          text
        >
          {{ app.title }}
        </v-btn>
        <v-btn
          v-if="$store.getters.isAuthenticated"
          @click="logout"
          depressed
          plain
          text
          >Logout</v-btn
        >

        <v-spacer></v-spacer>

        <v-responsive max-width="260">
          <v-text-field
            dense
            flat
            hide-details
            rounded
            solo-inverted
          ></v-text-field>
        </v-responsive>
      </v-container>
    </v-app-bar>

    <v-main class="grey lighten-3">
      <v-container>
        <v-row>
          <v-col cols="2">
            <v-sheet rounded="lg">
              <v-list color="transparent">
                <v-list-item v-for="n in 5" :key="n" link>
                  <v-list-item-content>
                    <v-list-item-title> List Item {{ n }} </v-list-item-title>
                  </v-list-item-content>
                </v-list-item>

                <v-divider class="my-2"></v-divider>

                <v-list-item link color="grey lighten-4">
                  <v-list-item-content>
                    <v-list-item-title> Refresh </v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list>
            </v-sheet>
          </v-col>

          <v-col>
            <v-sheet min-height="70vh" rounded="lg">
              <router-view></router-view>
            </v-sheet>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
export default {
  data() {
    return {
      apps: [],
    };
  },
  watch: {
    "$store.getters.isAuthenticated": function (newVal) {
      if (newVal) this.apps = this.$store.getters.getApps;
      else this.fetchPublicApps();
    },
  },
  created() {
    this.fetchPublicApps();
  },
  methods: {
    fetchPublicApps() {
      this.$axios.get("/public/apps").then((response) => {
        this.apps = response.data.apps;
        this.$store.dispatch("setApps", response.data.apps);
      });
    },
    logout() {
      this.$store.dispatch("logout");
      this.$router.push({ name: "start" });
    },
  },
};
</script>
