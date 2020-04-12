const path = require("path");

module.exports = {
  pluginOptions: {
    i18n: {
      locale: "en",
      fallbackLocale: "en",
      localeDir: "locales",
      enableInSFC: false
    }
  },
  css: {
    loaderOptions: {
      sass: {
        data: `
          @import "@/style/_variable.scss";
        `
      }
    }
  },
  configureWebpack: {
    resolve: {
      alias: {
        "@core": path.resolve(__dirname, "src/modules/core"),
        "@dashboard": path.resolve(__dirname, "src/modules/dashboard"),
        "@error": path.resolve(__dirname, "src/modules/error"),
        "@presentation": path.resolve(__dirname, "src/modules/presentation"),
        "@sign": path.resolve(__dirname, "src/modules/sign"),
        "@settings": path.resolve(__dirname, "src/modules/settings"),
        "@organization": path.resolve(__dirname, "src/modules/organization")
      }
    }
  }
};
