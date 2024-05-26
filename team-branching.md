# Team Branches Strategies

- 1. git pull origin main
  - get latest changes from main branch
- 2. git branch
  - check what branch you are on right now
- 3. git checkout -b branch-name
  - BYT-FeatureName-Name

## Pushing changes up main branch

- new Branch for first time
        ```
        git push --set-upstream origin "branch-name"
        ```
- 1. git add .
- 2. git commit -m "message about changes done in this commit"
- 3. git push
  - Once pushed make a "ticket" in the team channel and has been "approved" by another team member
  - git checkout KevinsAddFeatureBranch review code | run in your enviorment
  - original user would merge their code to main
