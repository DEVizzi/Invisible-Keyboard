🔒 Invisible‑Keyboard
Invisible‑Keyboard is a minimal Android proof‑of‑concept app originally built for Nexus 5 to evaluate the presence of tap‑jacking (overlay) vulnerabilities in Android applications. Created to simulate tap hijacking scenarios, it provides an invisible custom keyboard layer designed to help assess input interception risks.

⚙️ Features
Grid-based virtual keyboard overlay mimicking the Nexus 5 on‑screen keyboard layout.
Supports toggling between visible and invisible modes to test system behaviour under both conditions 
Lightweight Java implementation (Java 8), targeted for demonstration and dynamic analysis purposes 

🧪 Use Cases
Validate if an Android app is susceptible to tap‑jacking by overlaying an invisible keyboard UI.
Evaluate whether touch events pass through overlays unchecked by target applications.
Enhance understanding of Android's overlay permission model and touch event propagation.

🚀 Getting Started
Clone the repository:
git clone https://github.com/DEVizzi/Invisible-Keyboard.git
Open it in Android Studio and build using SDK tied to Nexus 5 screen dimensions.
Deploy to a device or emulator.
Enable or disable the keyboard grid to test tap interception under visible or invisible modes.

💡 Why It Matters
Android’s overlay (SYSTEM_ALERT_WINDOW) feature can be abused for tap‑jacking attacks—maliciously capturing user taps by drawing invisible views over legitimate UI. Visualizing this scenario with an invisible custom keyboard helps:
Demonstrate how touch events can be hijacked.
Aid in analyzing how vulnerable apps respond under overlay conditions.
Serve as a learning tool for security researchers and mobile developers interested in Android defense.

🛠️ Technical Notes
Built using standard Android UI components and a fixed Nexus 5 grid overlay design.
Requires manual selection of overlay permissions on modern Android versions.
No third‑party dependencies, providing full transparency for review and modification.
Ideal for experimentation, pentesting labs, or security training sessions.

🧭 Quick Overview
Attribute	Details
   Platform	Android (Nexus‑5 layout target)
   Language	Java / Android SDK
   Purpose	Tap‑jacking proof of concept
   Status	Stable, simple POC
   Version	1.0 (latest release: Dec 11, 2018) 


🙌 Want to Contribute?
While the primary goal was to design an educational example, contributions are welcome:
Adapting to other screen resolutions or device form‑factors.
Supporting dynamic layout generation for various Android devices.
Integrating automated tests or interactive security demos.

🚫 Disclaimer
Invisible‑Keyboard is intended only for ethical, educational, or security‑research purposes. Use responsibly on devices you control and with user consent. Misuse for malicious purposes is strictly prohibited under applicable laws.

📝 Summary
Invisible‑Keyboard delivers a clean and refined environment to understand, demonstrate, and experiment with tap‑jacking vulnerabilities on Android. Originally developed as a security research tool seven years ago, it remains a concise resource for anyone interested in overlay attacks, touch event hijacking, and Android input security.
Consider it a compact, intuitive starting point for developers and researchers learning about overlay‑based vulnerabilities—and feel free to extend or adapt it for modern Android testing environments!
