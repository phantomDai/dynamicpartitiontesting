import os


def change_import():
    current_path = os.getcwd()
    for file_name in os.listdir(current_path):
        if file_name == 'change_import_info.py':
            continue
        mutant_path = os.path.join(current_path, file_name, "MealOrderingSystem.java")
        file = open(mutant_path, 'r')
        content = []
        for aline in file:
            if "labprograms" in aline:
                content.append("import mutants.MOS.sourceCode.MSR;\n")
            else:
                content.append(aline)
        file.close()
        os.remove(mutant_path)
        new_file = open(mutant_path, 'w+')
        new_file.writelines(content)


change_import()
