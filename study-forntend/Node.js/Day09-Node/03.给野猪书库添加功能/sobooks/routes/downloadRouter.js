var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/dl/:bookid', function(req, res, next) {
  res.download('./public/downloadbook/雾都孤儿（读客经典）.epub')
});

module.exports = router;
