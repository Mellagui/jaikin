# 🌀 Jaikin

## 🎯 Objective

The goal of this project is to **implement Chaikin's algorithm** and create a **step-by-step animation** of the process using a canvas.

---

## 🧭 Instructions

Follow these steps to create the Chaikin's algorithm step-by-step animation:

1. 🖼️ **Create a Canvas** — Set up a canvas where the user can draw one or more points.  
   Each point should be represented by a small circle.

2. 🖱️ **Mouse Input** — Allow the user to place control points for Chaikin's algorithm using the **left mouse button**.

3. 🔵 **Display Points** — Show the selected points on the canvas as small circles marking each point.

4. ⌨️ **Animation Trigger** —  
   When the canvas has control points, pressing **Enter** should initiate the animation.  
   The animation must cycle through **7 steps** of Chaikin's algorithm.  
   After completing the 7th step, the animation should restart automatically.

5. 🚫 **No Points Case** —  
   If **Enter** is pressed before any points are drawn, nothing should happen.  
   Optionally, display a message prompting the user to draw control points.

6. ⚪ **Single Point Case** —  
   If the canvas contains only **one control point**, display that point without cycling through the steps.

7. 📏 **Two Points Case** —  
   If the canvas contains only **two control points**, draw a **straight line** between them.

8. ❌ **Exit Control** —  
   Pressing the **Escape** key should close the window.

---

## 🌟 Bonus Features

Optionally, you may implement the following enhancements:

1. 🧹 **Clear Canvas** — Allow the user to clear the screen and select new control points.

2. 🖐️ **Interactive Dragging** — Implement real-time dragging of control points, so users can adjust their positions dynamically.

---

## 📘 References

📄 Learn more about **Chaikin’s Algorithm**:  
👉 [Chaikin’s Algorithm PDF](https://www.cs.unc.edu/~dm/UNC/COMP258/LECTURES/Chaikins-Algorithm.pdf)

🎬 Watch an example of the application in action:  
👉 [YouTube Example](https://youtu.be/PbB2eKnA2QI)

> 💡 The use of **Chaikin’s algorithm** is mandatory for this implementation.

---

## 🏁 Expected Result

By following the steps above, your program should:
- Allow users to create and visualize control points.
- Animate the Chaikin subdivision process up to 7 iterations.
- Restart automatically for continuous demonstration.

## Project Structure

```
jaikin/
├── src/
├── .gitignore
├── build.sh
└── README.md
```
