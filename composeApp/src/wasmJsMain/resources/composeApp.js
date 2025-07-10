<script type="module">
  // Import the functions you need from the SDKs you need
    import { initializeApp } from "https://www.gstatic.com/firebasejs/11.10.0/firebase-app.js";
      import { getAnalytics } from "https://www.gstatic.com/firebasejs/11.10.0/firebase-analytics.js";
        // TODO: Add SDKs for Firebase products that you want to use
          // https://firebase.google.com/docs/web/setup#available-libraries

            // Your web app's Firebase configuration
              // For Firebase JS SDK v7.20.0 and later, measurementId is optional
                const firebaseConfig = {
                    apiKey: "AIzaSyC1cVEyuTl2mCEdzf10sUWg4B-RI10ZrIM",
                        authDomain: "fabicx.firebaseapp.com",
                            databaseURL: "https://fabicx-default-rtdb.firebaseio.com",
                                projectId: "fabicx",
                                    storageBucket: "fabicx.firebasestorage.app",
                                        messagingSenderId: "833897904391",
                                            appId: "1:833897904391:web:91d9cefdf00a6eff70eda2",
                                                measurementId: "G-3CLV9T4ZJ8"
                                                  };

                                                    // Initialize Firebase
                                                      const app = initializeApp(firebaseConfig);
                                                        const analytics = getAnalytics(app);
                                                        </script>