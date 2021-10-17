/**
 * @param {number} x
 * @return {boolean}
 */
function isPalindrome(x) {
  if (x < 0) return false;

  let y = 0; // New number
  for (let _x = x; _x > 0; _x = Math.floor(_x / 10)) {
    y *= 10;
    y += _x % 10;
  }

  return x === y;
}
