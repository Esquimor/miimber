<template>
  <TemplateSign>
    <template v-slot:rigth>
      <v-form v-model="valid">
        <v-text-field
          v-model="email"
          :rules="emailRules"
          label="First name"
          required
        ></v-text-field>
      </v-form>
    </template>
    <template v-slot:left>
      <div class="Login-img" />
    </template>
  </TemplateSign>
</template>

<script>
"use strict";

import TemplateSign from "@/template/TemplateSign";

export default {
  name: "Login",
  components: {
    TemplateSign
  },
  data() {
    return {
      valid: false,
      email: "",
      emailRules: [
        v => !!v || "E-mail is required",
        v => /.+@.+/.test(v) || "E-mail must be valid"
      ],
      password: ""
    };
  }
};
</script>

<style lang="scss">
.Login {
  &-form {
    display: flex;
    flex-direction: column;
  }
  &-img {
    width: 100%;
    height: 100%;
    background: linear-gradient(#1565c0, #42a5f5);
  }
}
</style>
