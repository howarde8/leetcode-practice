/**
 * @param {number} x
 * @return {boolean}
 */
function isPalindrome(x) {
  if (x < 0) return false;
  let str = `${x}`; // Convert x to string by template literals

  let l = 0;
  let r = str.length - 1;
  while (l < r) {
    if (str.charAt(l) !== str.charAt(r)) return false;
    l += 1;
    r -= 1;
  }

  return true;
}
