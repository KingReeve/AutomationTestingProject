# Git Branching

One of the main benefits of using Git collaboratively is the ability to make use of the git version control and update your local files with updates that have been pushed into the shared remote repository. If you have branch protections in place (and you should) it is useful to come up with a "branching strategy" that is shared across your team and actually followed.

## Common Branching Strategies

- protect the main branch from direct pushes
  - even from the admin
  - yes that means you
- use a common naming strategy for your work branches
  - in the planetarium, you might follow Jira's lead and us the initial P to start with, then perhaps the name of the epic you are working on (or feature of it is End to End testing), the user story, name of who is working on the feature, etc.
    - examples:
      - P-Registration-PositiveTests-EricS
      - E2E-UsersShouldBeAbleToLogin-EricS
      - etc.
- consider having sub branches that you merge your work into that are then periodically merged into the main branch
  - main
    - user
      - registration-work-branch-EricS
      - Login-work-branch-BillyB
      - etc.
    - planet
    - moon

## Some Git commands to help with this process

the following command creates a new branch locally with the given name. It also switches you over

```git
git checkout -b branch-name
```

the following command will merge content from your targeted branch into your current active branch

```git
git merge main
```

the following command will pull content from a remote repository branch that you specified. This can be used to shortcut getting new content in the main branch into your local dev branch

```git
git pull origin main
```

the following command will display the current local branches available

```git
git branch
```
