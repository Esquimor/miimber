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
        "@home": path.resolve(__dirname, "src/modules/home"),
        "@sign": path.resolve(__dirname, "src/modules/sign")
      }
    }
  }
};
