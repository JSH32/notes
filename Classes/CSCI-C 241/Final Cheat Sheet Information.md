---
created: 2024-05-01T02:44
updated: 2024-05-01T04:30
---
For a set $S$, the cardinality of the powerset of $S$ is $2^{|S|}$, where $|S|$ is the cardinality of the set $S$. This reflects the fact that each element of the set can either be included or not included in each subset, resulting in two choices per element. Therefore, for a set with $n$ elements, the number of possible subsets (which is the size of the powerset) is $2^n$.

- Causality indicators: because, since, due to
    - Implication types:
        - $P \rightarrow Q$: "if $P$, then $Q$"; "$Q$ if $P$"; "$P$ only if $Q$"; "$P$ is sufficient for $Q$"; "$Q$ is necessary for $P$"
        - $P \leftrightarrow Q$: "$P$ is necessary and sufficient for $Q$"
        - "$P$ is sufficient for $Q$": $P \rightarrow Q$
        - "$P$ is necessary for $Q$": $Q \rightarrow P$
- Logical Connectives: $\neg, \land, \lor, \oplus, \rightarrow$
- Contradictions: Always FALSE
- Tautology: Always TRUE
- Existential Claims:
    - Proven by providing an example with the property
    - Truth Assignment
- Universal Claims:
    - Proven by demonstrating all instances have the property
    - Truth Table
- Consistency:
    - A set of formulas is consistent if there is an assignment satisfying all formulas
    - Provable with a truth assignment, disprovable with a truth table
- Logical Equivalency $\equiv$:
    - Formulas are logically equivalent if every satisfying assignment for one satisfies the other
    - Provable with matching truth tables, disprovable with a truth assignment
- Validity:
    - Valid if every satisfying assignment for premises satisfies conclusion
    - Invalid if there exists a satisfying assignment for premises not satisfying conclusion


- **First-Order Logic:**
    $∀x(P(x)→Q(x))$
    **Translation:** "For all xx, if xx has property PP, then xx also has property QQ."
    
    $∃x(P(x)∧¬Q(x))$
    **Translation:** "There exists an xx such that xx has property PP but does not have property QQ."
    $∀x∀y((R(x,y)∧R(y,x))→x=y)$
    **Translation:** "For all xx and yy, if xx is related to yy and yy is related to xx, then xx and yy must be the same."

**Relation on Str where symmetric and not transitive**
$g(x,y)$ = $x$ and $y$ share a letter in common
**Relation on Str where not One-To-One**
$g(x,y)$ = $\{{(x,y) | y=}$ first letter of x $\}$

- **Reflexive Relation:** A relation is reflexive if every element is related to itself.
    - The substring relation is reflexive because every string is a substring of itself.
- **Antireflexive Relation:** A relation is antireflexive if no element is related to itself.
	- A relation $R$ is antireflexive if for every element $x$, the pair $(x,x)∉R$
- **Symmetric Relation:** A relation is symmetric if for all $x$ and $y$, if $(x,y)$ is in the relation, then $(y,x)$must also be in the relation.
- **Antisymmetric Relation:** A relation is antisymmetric if for all $x$ and $y$, if $(x,y$) and $(y,x)$ are both in the relation, then $x$ must be equal to $y$.
- **Transitive Relation:** A relation is transitive if for all $x$, $y$, and $z$, if $(x,y)$ and $(y,z)$ are in the relation, then $(x,z)$ must also be in the relation.


The number of permutations (or ordered arrangements) of rr items taken from a set of n distinct items
$nPr=\frac{n!}{(n−r)!}​$

The number of combinations (or selections) of r items from a set of n distinct items where the order of selection does not matter.
$nCr=\frac{n!}{r!(n-r)!}$