import Lang from 'vue-lang'
import Vue from 'vue'

const locales = {
    "en": require("./locale/en.js"),
    "nl": require("./locale/nl.js")
}

Vue.use(Lang, { lang: 'en', locales: locales })