# Compose ChatGPT (In Progress):beers::beers: 

<p align="justify">
Jetpack Compose ChatGPT app is a sample ChatGPT app built with Jetpack Compose.
<img src="https://res.cloudinary.com/apideck/image/upload/v1672442492/marketplaces/ckhg56iu1mkpc0b66vj7fsj3o/listings/-4-ans_frontend_assets.images.poe.app_icon.png-26-8aa0a2e5f237894d_tbragv.png?raw=true" width="160px" height=auto align="right" alt="Computador"/>
To try out this sample app, use the latest stable version of <a href="https://developer.android.com/studio">Android Studio</a>. You can clone this repository or import the project from Android Studio following the steps <a href="https://developer.android.com/jetpack/compose/setup#sample">here</a>.
</p>

## Screenshots

<p>
<img src="https://github.com/lambiengcode/compose_chatgpt_kotlin/blob/main/screenshots/demo_chatgpt_lite.gif?raw=true" width="200px"/>
<img src="https://github.com/lambiengcode/compose_chatgpt_kotlin/blob/main/screenshots/demo_1.jpeg?raw=true" width="200px"/>
<img src="https://github.com/lambiengcode/compose_chatgpt_kotlin/blob/main/screenshots/demo_2.jpeg?raw=true" width="200px"/>
</p>

## Setup project to run in your local machine

***Setup Firebase***
- Create your firebase project and enable ***Firestore***
- Put file ***google-services.json*** into ***app/***

***Setup OpenAI ApiKey***
- Generate api key from OpenAI console: https://platform.openai.com/account/api-keys
- Edit value of ***openAIApiKey*** in file ***app/src/main/java/com/chatgptlite/wanted/constants/Constants.kt*** to your key


## Features

- [x] New Chat
- [x] Chat History
- [x] ChatBot with ChatGPT
- [ ] Stop generating
- [ ] Delete conversation
- [ ] Settings for ChatGPT
- [ ] Light/Dart Themes

### :butterfly: License

```terminal
MIT License

Copyright (c) 2022 lambiengcode

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
