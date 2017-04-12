var merge = require('webpack-merge')
var prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  API_URL: '"http://localhost:8080/KwetterProject_1-web-1.0-SNAPSHOT/rest/"'
})
