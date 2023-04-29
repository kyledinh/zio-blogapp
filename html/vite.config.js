import {resolve} from 'path'
import { createHtmlPlugin } from 'vite-plugin-html'

// const scalaVersion = '2.13'
const scalaVersion = '3.1.3'

// https://vitejs.dev/config/
export default ({mode}) => {
    // const mainJS = `./frontend/target/scala-${scalaVersion}/pet-clinic-frontend-${mode === 'production' ? 'opt' : 'fastopt'}/main.js`
    const mainJS = `./main.js`
    const script = `<script type="module" src="${mainJS}"></script>`

    return {
        plugins: [
            createHtmlPlugin({
                minify: true,
                inject: {
                    data: {
                        script
                    }
                }
            })
        ],
        resolve: {
            alias: {
                'stylesheets': resolve(__dirname, './frontend/src/main/static/stylesheets'),
            }
        }
    }
}
