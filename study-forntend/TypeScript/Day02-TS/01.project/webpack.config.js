// 引入一个包
const path = require('path')
// 引入html插件
const HTMLWebpackPlugin = require('html-webpack-plugin')
// 引入clean插件
const { CleanWebpackPlugin } = require('clean-webpack-plugin')

/*
    // 初始化项目
    npm init -y
    // 安装 webpack webpack-cli typescript ts-loader
    npm i -D webpack webpack-cli typescript ts-loader
    // 安装 html-webpack-plugin       自动生成html并引入打包好的js
    npm i -D html-webpack-plugin
    // 安装 webpack-dev-server        内置服务器，热更新
    npm i -D webpack-dev-server
    // 安装 clean-webpack-plugin      清除旧的打包好的文件夹
    npm i -D clean-webpack-plugin
    // 安装babel
    npm i -D @babel/core @babel/preset-env babel-loader core-js

 */

// webpack中的所有配置信息都应该写在module.exports中
module.exports = {

    // 指定模式 不指定webpack会报警告 production(产品模式) development(开发模式)
    mode: "production",

    // 指定入口文件
    entry: "./src/index.ts",

    // 指定打包文件所在目录
    output: {
        // 指定打包文件的目录
        path: path.resolve(__dirname, 'dist'),
        // 打包文件后的文件名
        filename: "bundle.js",
        // 告诉webpack不适用箭头函数
        environment: {
            arrowFunction: false
        }
    },

    // 指定webpack打包时要使用的模块
    module: {
        // 指定要加载的规则
        rules: [
            {
                // test指定的是规则生效的文件
                test: /\.ts$/,
                // 要使用的loader
                // 加载顺序从后往前执行
                use: [
                    // 配置babel
                    {
                        // 指定加载器
                        loader: "babel-loader",
                        // 配置babel
                        options: {
                            // 设置预定义环境
                            presets: [
                                [
                                    // 指定环境的插件
                                    "@babel/preset-env",
                                    // 配置信息
                                    {
                                        // 要兼容的目标浏览器
                                        targets: {
                                            // 需要兼容chrome 88
                                            "chrome": "58",
                                            "ie": "11"
                                        },
                                        // 指定core.js版本
                                        "corejs": "3",
                                        // 使用corejs的方法 usage表示按需加载
                                        "useBuiltIns": "usage"
                                    }
                                ]
                            ]
                        }
                    },
                    'ts-loader'
                ],
                // 要排除的问价夹
                exclude: /node_modules/
            }
        ]
    },

    // 配置webpack插件
    plugins: [
        // 这个插件自动帮我们生成html文件，并将打包好的bundle.js文件引进去
        new HTMLWebpackPlugin({
            // html 的title
            // title: "这是一个自定义的title",

            // 指定模板html
            template: "./src/index.html"
        }),
        // 先清空打包好的文件目录 在将打包的文件放入
        new CleanWebpackPlugin(),

    ],

    // 设置引用模块
    resolve: {
        extensions: ['.ts', '.js']
    }
}