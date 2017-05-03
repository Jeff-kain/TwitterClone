// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store/'
import request from './utils/request'
import iView from 'iview'
import 'iview/dist/styles/iview.css';

Vue.use(iView);
var lang = require('./lang');
Vue.use(lang);

Vue.prototype.$http = request
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})
