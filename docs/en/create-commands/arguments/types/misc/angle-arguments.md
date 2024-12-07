---
order: 1
authors: 
  - JorelAli
---

# Angle arguments

The angle argument is used to represent the _yaw_ (horizontal) angle in degrees. The value returned from this argument range from -180.0 (inclusive) to 180 (exclusive), with -180.0 being due north:

$$
\begin{array}{c}
& N (-180°) & \\
& \uparrow & \\
& & \\
& & \\
W (90°) \leftarrow\hspace{0.4em} & \bullet & \hspace{0.4em}\rightarrow E (-90°) \\
& & \\
& & \\
& \downarrow & \\
& S (0°) & \\
\end{array}
$$


The `~` notation can be used to specify a rotation relative to the executor's yaw angle.