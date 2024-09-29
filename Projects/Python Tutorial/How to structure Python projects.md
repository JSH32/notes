---
created: 2024-09-29T18:56
updated: 2024-09-29T19:16
---
## Introduction
Here we will explain how to properly structure your Python projects for scalability, clarity, and ease of use. Managing dependencies with Poetry is just one piece of the puzzle. We will also explore how to organize your code into modules, the role of `__init__.py`, and how you can use functions across different Python files. By the end of this chapter, you will not only know how to handle dependencies with Poetry but also how to structure the internal logic of your project.
## What is a Dependency?
Before we proceed, let's clarify what a **dependency** is. A **dependency** is an external package that your project needs to function. For instance, if your project requires working with APIs, you may include the `requests` library as a dependency. As projects grow, managing dependencies becomes crucial, and this is where tools like **Poetry** come into play.

## Why Use Virtual Environments?
A **virtual environment** isolates your project’s dependencies from the system’s global Python installation. This is useful when you have multiple projects that require different versions of the same library. They are stored using managers like Conda or in folders using python's own `virtualenv` tool.
### Benefits of Virtual Environments

1. **Isolation**: Avoids conflicts between project dependencies.
2. **Reproducibility**: Guarantees that your code works the same across different setups.
3. **Portability**: Simplifies sharing your project with others while ensuring it runs in the same environment.

Without virtual environments, managing multiple projects that require different dependencies can lead to compatibility issues.

## What is Poetry?

**Poetry** automates dependency management, virtual environments, and task automation, making it a great tool for Python projects. It simplifies:

1. **Dependency Management**: Automatically resolves and installs dependencies, keeping them in a `pyproject.toml` file so anyone can re-install dependencies at a later point.
2. **Virtual Environment Setup**: Creates and manages a virtual environment for your project.
3. **Lock File**: Ensures consistent dependency versions across machines via the `poetry.lock` file.
4. **Task Automation**: Allows you to define and automate common tasks.
5. **Project Packaging**: Simplifies building and publishing your project as a Python package.

### Installing Poetry
To start using Poetry, you need to install it on your system. Poetry is compatible with Python 3.7 and above. Here’s how to install it:

1. Open a terminal or command prompt.
2. Run the following command to download and install Poetry
```bash
curl -sSL https://install.python-poetry.org | python3 -
```
This will download and install the latest version of Poetry.

3. After the installation is complete, you might need to add Poetry to your system’s PATH so you can access it from the terminal. To do this, follow the instructions printed at the end of the installation process. Typically, you can run a command like this:
4. `export PATH="$HOME/.local/bin:$PATH"`
5. Verify poetry is installed by running `poetry --version`

## Structuring Your Python Project
Now that we’ve set up Poetry, let's focus on structuring a Python project. A well-structured project improves maintainability and readability, especially as it grows.
### Basic Structure

Here’s a common structure for Python projects with examples files.
```
my_project/
│
├── my_project/
│   ├── __init__.py
│   ├── core.py
│   └── utils.py
├── tests/
│   └── test_core.py
├── pyproject.toml
├── README.md
└── LICENSE
```
* **`my_project/`**: This directory contains the source code of the project or module
* **`__init__.py`**: Marks the directory as a Python package. It allows you to import modules from this directory. This is the origin point of a module.
* **`pyproject.toml`**: The configuration file that defines your dependencies and project metadata and poetry dependencies.
* **`README.md`**: Documentation for the project.
* **`LICENSE`**: The license file for the project, only needed when publishing.

### What is `__init__.py`?

The `__init__.py` file is important for organizing your project into a **package**. It can be an empty file, but its presence tells Python that the directory should be treated as a package, allowing you to import its contents. For example, with the following code, someone can import from your project, or you can import code from another module within the same poetry project.
```python
from my_project.core import some_function from my_project.utils import helper_function
```

You can also use `__init__.py` to control what gets imported when someone imports the package directly
```python
# my_project/__init__.py
from .core import some_function
from .utils import helper_function

# Now you can do this in another file: 
from my_project import some_function, helper_function
```

#### Important Note
This is not incredibly important when you are making executables, or code that executes on it's own, but when you make subdirectories within your project, you should usually treat those subdirectories as a module on it's own. Since generally those subdirectories don't execute standalone.

## Organizing Code Across Files
Breaking your code into smaller, more focused modules makes it easier to manage. Here’s an example of how you might divide your project into `core.py` and `utils.py`.

```python
# my_project/core.py
def some_function():
    print("This is the core functionality.")
```

```python
# my_project/utils.py
def helper_function(): 
	print("This is a utility function.")
```

By organizing your code this way, each module has a clear responsibility, making your project more maintainable and reusable.

### Task: Use Functions Across Files
Let’s try using the functions from `core.py` and `utils.py` in a main script. Create a `main.py` file in the `my_project` directory

```python
# my_project/main.py
from core import some_function
from utils import helper_function

def main():
    some_function()
    helper_function()

if __name__ == "__main__":
    main()
```

To run this script, go in the virtual environment using `poetry shell` and then run `python my_project/main.py`

### NOTE
This part of the code
```python
if __name__ == "__main__":
    main()
```
will only execute if the python code is called directly from the shell. Python interpreter will automatically set the global per-script variable `__name__` to `__main__` if this happens.

## Poetry Tasks
In addition to organizing code, **Poetry** can help automate tasks like running scripts or testing. You can define **tasks** in the `pyproject.toml` file under `[tool.poetry.scripts]`.

### Defining a Task in Poetry
Edit `pyproject.toml` and add a task:
```toml
[tool.poetry.scripts]
run_project = "my_project.main:main"
```

Now, instead of typing `python my_project/main.py` every time, you can run
```bash
poetry run run_project
```

This makes it easier to run key parts of your project and ensures they’re executed within the correct virtual environment. You can also use this instead of using the `__name__` variable.

## Challenge: Structure Your Own Project

Now it’s time to try it out yourself

1. **Create a New Project**: Use `poetry new calculator` to start.
2. **Add a Function**: Create two files, `calculator.py`, in your `my_project` directory. Add a function in each file.
3. **Use `__init__.py`**: Modify `__init__.py` to allow importing the functions from both files.
4. **Create a Task**: Add a task in `pyproject.toml` to run the main script.
5. **Test Your Setup**: Run your task using `poetry run <task_name>`.