import * as m1 from './01.分别暴露.js'
import * as m2 from './02.统一暴露.js'
import * as m3 from './03.默认暴露.js'

// console.log(m1)
// console.log(m2)
// console.log(m3)

// m1.teacher()
// m2.findJob()
// m3.default.change()

// 修改背景颜色为粉色
import $ from 'jquery' // const $ = require('jquery')
$('body').css('background', 'pink')
