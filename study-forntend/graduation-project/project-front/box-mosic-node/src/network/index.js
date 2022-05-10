const axios = require('axios');
const { options }  = require('../constant/network');

let instance = axios.create(options);
module.exports = instance;