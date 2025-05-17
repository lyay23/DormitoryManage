// 跨域配置
module.exports = {

    publicPath: './',

    devServer: {                
        
        proxy: {                 
            '/api': {             
                target: 'http://localhost:9090',     
                changeOrigin: true,              //是否设置同源，输入是的
                pathRewrite: {                   //路径重写
                    '^/api': ''                     //选择忽略拦截器里面的内容
                }
            }
        }
    }
}

