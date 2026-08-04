---
name: dsa-problem-solver
description: Solves DSA/competitive-programming problems in this Java repo by researching the community-accepted approach online, writing a brute force and one or more accepted optimized solutions in one file, and stress-testing them against each other on generated test cases. Use when the user pastes a LeetCode/Codeforces-style problem statement, asks for an algorithm or optimal solution, mentions time-limit or complexity concerns, or asks to verify an existing solution against brute force.
---

# DSA Problem Solver

Every solved problem lands as a single self-contained Java file. Every method in it — each optimized one and the brute force — carries the **same** header comment block and step-by-step comments in the body. The optimized solution is the approach the community actually accepts for this problem, not the cleverest bound that can be squeezed out of it, and its header links the source problem. Correctness is established by randomized differential testing against the brute force, never by eyeballing the samples, and the stress harness is kept afterwards so the check can be re-run.

## File layout

```
<dir>/<Class>.java
  ├── header block  →  optimized method            (the standard accepted approach)
  ├── header block  →  optimized variant, optional (another famous accepted approach)
  ├── header block  →  brute force method          (kept, never deleted)
  └── main          →  samples + edge cases, expected value in a trailing comment

temp/<Class>Stress.java   (kept, never deleted)
```

## Workflow

Copy this checklist and track progress:

```
- [ ] 1. Research the pattern and the community-accepted approach
- [ ] 2. Pick the directory and class name
- [ ] 3. Write the brute force first, with its header block
- [ ] 4. Write the accepted optimized solution, with its header block
- [ ] 5. Comment the body of every method
- [ ] 6. Run the sample cases
- [ ] 7. Stress-test every optimized method vs brute force
- [ ] 8. Report
```

## The header block

Every method uses this identical template. Only the values differ — never the field set, never the order.

```java
/*
 * <Problem title> (<source, e.g. LeetCode 1636 / Codeforces 1857B / GFG>) — <Optimized: named technique | Brute force>
 *
 * <One-sentence statement of what this method computes.>
 *
 * Source:   <direct URL to the problem, or "no exact match found — derived">
 * Similar:  <closest canonical problem this pattern comes from + URL>
 *
 * Approach:
 * - <first phase, in execution order>
 * - <second phase>
 * - <how the answer is combined: min / max / first that works / count>
 * - <the base, termination or fallback case>
 *
 * Pattern:  <named technique>
 * Insight:  <why this is correct>
 * Time:     O(...)  where n = ..., V = ...
 * Space:    O(...)
 */
```

### Filling the fields

**`Source`** — a clickable URL, not just a number, so the statement can be re-read without a search. Every method in the file repeats the same `Source`, including the brute force. When the exact problem is not online (an OA question, a reworded variant), write `no exact match found — derived` and let `Similar` carry the link.

**`Similar`** — the canonical problem whose pattern this solution borrows, with its URL, plus a few words on how this problem differs from it (`ties ascending here, descending on 1636`). Omit the field only when the `Source` link *is* the canonical problem and nothing closer exists. When a variant is tuned from a known problem, this field is what tells the reader where the pattern is documented.

**Title line** — for optimized methods, name the technique after `Optimized:` (`Optimized: counting sort`, `Optimized: heap of buckets`) so multiple optimized methods in one file are told apart at a glance.

**`Approach`** — one bullet per phase, in execution order, written in the vocabulary of the problem (monsters, planks, tasks) rather than the vocabulary of the code (loops, indices). A reader must be able to confirm the method matches the problem statement without reading a line of code. Three to six bullets; if you need more, the method is doing too much.

**`Pattern`** — the named technique, using the name the community uses for it so the reader can search it (`difference array + backward greedy`, `counting sort on frequency`, `monotonic stack`). For the brute force it is the shape of the search instead (`exhaustive enumeration over bitmask subsets`, `linear scan over every candidate answer`, `direct simulation of the stated rules`).

**`Insight`** — the blocks differ only in what makes them trustworthy, and this is the field that carries it.

- Optimized: the non-obvious justification for the shortcut. The fact that makes the greedy safe, the reason two cases can never compete for the same resource, the invariant the loop maintains. State *why it is correct*, not what the code does. This is the most important line in the file.
- Brute force: why it cannot be wrong — it enumerates every candidate and takes no shortcut the statement does not grant. If you cannot write that honestly, the brute force is too clever and should be rewritten more stupidly.

**`Time` / `Space`** — for the brute force, add `deliberately slow; only ever run on n <= 8` after the bound, so nobody mistakes it for a fallback implementation.

## Steps

### 1. Research the pattern and the community-accepted approach

Search the web before writing code, even when an approach seems obvious. You are looking for the solution the community converged on — the editorial approach, the top-voted discussion answer, the shape every accepted submission has — not the tightest theoretical bound and not someone's code to copy.

- Search the distinctive phrasing of the problem plus the year, and search the abstract shape of it (e.g. `"pair elements summing to target" maximize count algorithm`).
- Prefer the editorial, the problem's top-voted discussion threads, Leetcode, GeeksforGeeks, and competitive-programming references (cp-algorithms, USACO guide) over blog spam.
- **Find the source problem and keep its URL.** LeetCode, GFG, Codeforces, InterviewBit — whatever the statement actually is. This URL goes in the header block.
- **When the exact problem is not online**, find the nearest known problem and solve *this* problem with *that* pattern, tuned to the differences. Keep that problem's URL for the `Similar` field and name the difference explicitly (tie-break direction reversed, queries added, values unbounded).
- Identify: the **pattern name**, the **complexity the accepted solutions have**, and the **constraints**. Constraints decide the target — state which one you are aiming for.
- **Note every other approach that is also commonly accepted** for this problem (sorting vs heap vs counting, DP vs greedy, two pointers vs binary search). These are candidates for step 4.
- If the search returns nothing useful after two or three queries, stop searching and derive the approach yourself. Say so explicitly, and write `no exact match found — derived` in the header rather than inventing a citation.

Report the pattern, the source link, and the intended complexity to the user before writing the optimized code.

### 2. Pick the directory and class name

Place the file in the existing directory matching the pattern: `array`, `bit`, `dynamic_programming`, `graph`, `list`, `sorting`, `string`, `sweep_line`. Create a new lowercase snake_case directory only when the pattern genuinely fits none of them (e.g. `trie`, `two_pointers`).

Repo conventions to match:

- No `package` declaration.
- One public class per file, `PascalCase`, filename matches the class.
- A `public static void main` that instantiates the class and prints each sample case with the expected value in a trailing comment.
- `.class` files and `temp/` are gitignored, so compiling in place and scratch-building in `temp/` leave the working tree clean.

### 3. Write the brute force first, with its header block

Write the obviously-correct exhaustive version before the clever one, so it can't be unconsciously shaped to match the optimized logic. Name it `<methodName>Brute`. Exponential or O(n³) is fine — it only ever runs on tiny inputs.

Translate the problem statement directly: simulate the stated rules, enumerate every candidate, take no shortcut. It stays in the final file permanently as an executable statement of what the optimized method is claiming.

### 4. Write the accepted optimized solution, with its header block

The optimized methods go **above** the brute force in the file, each under its own copy of the template.

**Write the approach the community accepts, not the most optimized one you can construct.** These files are study material and interview preparation: the reader needs the solution they would be expected to produce and to recognise elsewhere. A `HashMap` plus a comparator sort that everyone writes beats a hand-rolled radix trick that shaves a log factor nobody asked for. Prefer the standard approach when it is:

- what the editorial and the top-voted answers use, and
- fast enough for the stated constraints.

Only reach past it when the constraints genuinely reject it — and then say so, naming the bound that fails (`n log n at n = 10^6 with q = 10^5 queries per test is too slow, so the accepted solutions all use counting`).

When the exact problem is not online, implement the nearest known problem's pattern tuned to this statement, and keep it recognisable as that pattern. Do not redesign it into something the reader cannot map back to the documented problem.

**Multiple optimized methods are allowed and encouraged** when more than one approach is genuinely famous and accepted — counting sort *and* comparator sort, DP *and* greedy, heap *and* bucket. Rules:

- The **first** method is the one you recommend: the most commonly accepted approach for these constraints.
- Name alternates `<methodName><Technique>`: `sortByFrequencyComparator`, `sortByFrequencyHeap`, `maxProfitDp`.
- Each gets its own full header block, including its own `Pattern`, `Insight`, `Time` and `Space` — the whole point is that the reader can compare them.
- Every alternate is stress-tested in step 7 exactly like the primary. An untested variant is worse than no variant.
- Stop at two or three. Do not enumerate every possible technique; a file of near-duplicates teaches nothing.

Aim at the complexity the constraints imply, and say which one you are targeting. If you had to assume a constraint the statement omitted, record it in the header block and repeat it in the report.

### 5. Comment the body of every method

These files are study material, so the header block is not enough on its own — annotate the code itself. In **every** method, put a short comment above each meaningful step:

- Each distinct phase: building the frequency map, the difference-array pass, the prefix-sum pass, the backward sweep, the final combine.
- Every non-obvious branch, naming which case it handles and why (`req > 0` means the suffix still needs strength, so clamping to zero cannot help).
- Every loop, stating what it iterates over and what it accumulates.
- Every deliberate type or bound: `long` because sums reach 2 * 10^9, `n + 1` because the difference array needs a slot past the last index.

Explain the *step*, not the syntax. `// pair each value with its complement and bank the smaller count` is useful; `// increment i` is noise. Aim for a comment every three to five lines in a dense algorithm, fewer in plain setup code.

### 6. Run the sample cases

```bash
cd <dir> && javac <Class>.java && java <Class>
```

Every sample from the problem statement must pass before moving on. Include the samples plus obvious edge cases in `main`: single element, all-identical elements, the smallest legal input, and any case where the answer is legitimately zero.

### 7. Stress-test every optimized method vs brute force

This step is not optional and it is what makes the answer trustworthy. Write the harness as `temp/<Class>Stress.java` and **leave it there** after running — it is the reproducible proof of the claim and lets the check be re-run after any later edit. `temp/` is gitignored, so keeping it does not dirty the working tree.

Every optimized method is compared against the brute force on the same input, so a single mismatch report names which variant broke.

```java
import java.util.Random;

public class <Class>Stress {
    public static void main(String[] args) {
        <Class> solver = new <Class>();
        Random rnd = new Random(42);

        // Round 1: mixed shapes.
        for (int t = 0; t < 3000; t++) {
            int n = 1 + rnd.nextInt(7);
            int[] input = new int[n];
            for (int i = 0; i < n; i++) {
                input[i] = 1 + rnd.nextInt(8);
            }
            long slow = solver.<method>Brute(input);
            // Every accepted variant is checked against the same brute-force answer.
            if (!check(input, "primary", solver.<method>(input), slow)) return;
            if (!check(input, "heap", solver.<method>Heap(input), slow)) return;
        }
        System.out.println("all ok (3000 random)");
    }

    private static boolean check(int[] input, String name, long fast, long slow) {
        if (fast != slow) {
            System.out.println("MISMATCH [" + name + "] " + java.util.Arrays.toString(input)
                    + " fast=" + fast + " slow=" + slow);
            return false;
        }
        return true;
    }
}
```

```bash
cd <repo root> && javac -d /tmp/stress <dir>/<Class>.java temp/<Class>Stress.java && java -cp /tmp/stress <Class>Stress
```

Generator rules that decide whether this catches anything:

- **Tiny sizes, small value ranges.** `n <= 8` and values in `1..8`. Small ranges force duplicates and collisions, which is where greedy and counting arguments break.
- **Fixed seed** so a failure is reproducible.
- **Print the failing input** and stop at the first mismatch.
- **Add a labelled round per structural shape** the problem has, each printing its own `all ok (...)` line: sorted input, all-equal values, no optional input at all, one range covering everything, extreme values at the constraint bounds, negatives and zero when the constraints allow them.

On a mismatch: fix the optimized solution, not the brute force, unless you can show the brute force misreads the problem. Re-run until clean. If the samples disagree with the brute force, the problem was misunderstood — re-read the statement before touching either.

### 8. Report

Nothing is deleted: the brute force stays in the solution file, the harness stays in `temp/`.

Report to the user in this order:

1. The result — file path, and that the samples plus N randomized cases pass against the brute force, for every optimized method in the file.
2. The source — the problem link you used, and when the exact problem was not online, the nearest problem plus the difference you tuned for.
3. The insight, in prose. Why the optimization is correct.
4. A code reference to the core loop, the complexity, and any constraint assumption you had to make.
5. When the file has more than one optimized method: a short line per variant naming its pattern and complexity, and which one you recommend and why.
6. The harness path and the one-line command to re-run it.

## Notes

- Do not claim a solution is correct on the strength of the provided samples. Samples are the weakest possible evidence; the stress test is the claim.
- Favour the widely-taught solution over a marginally faster obscure one. If you skip the standard approach, the report must name the constraint that rules it out.
- Never present an unverified link. If you cannot find the problem, say so and write `no exact match found — derived` in the header rather than guessing a problem number or URL.
- When constraints are absent from the statement, pick the complexity target that a typical version of the problem implies, and state that assumption in the report and in the header block.
- If the accepted approach needs a data structure the repo lacks (segment tree, DSU), write it as a private static nested class in the same file rather than adding a dependency.
- Watch the return type before the algorithm: accumulated answers frequently overflow `int` even when every input fits in one. Use `long` in every method so the stress comparison stays honest.
