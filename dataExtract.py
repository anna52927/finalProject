#compilation of programs
#requires tabulate(also jpype)
#Written by Felix
import csv,json

try:
    import tabula
except ImportError:
    import os
    os.system('pip install jpype1')
    os.system('pip install tabulate')
    import tabula

def dataConvert(fileName,schoolName):
    tabula.convert_into(fileName, schoolName + "Data.csv", output_format="csv", pages="all")
    return schoolName + "Data.csv"

def organize(schoolName,dataSetName,startIndicator,sentinel,delayStart=0,delaySentinel=0,occurrence=1):
    with open(schoolName + 'Data.csv') as f:
        with open(schoolName + dataSetName + '.csv','w') as w:
            data = csv.reader(f)
            writer = csv.writer(w)
            write = False
            occurrence -= 1
            startCountdown = endCountdown = -1

            for line in data:
                if startIndicator in line and occurrence == 0:
                    startCountdown = delayStart
                elif startIndicator in line:
                    occurrence -= 1
                if sentinel in line:
                    endCountdown = delaySentinel
                if startCountdown == 0:
                    write = True
                if write:
                    writer.writerow(line)
                if endCountdown == 0:
                    write = False

                startCountdown -= 1
                endCountdown -= 1

    return schoolName + dataSetName + '.csv'                

def cleanElement(element):
    if element.lower() == 'n/a':
        element = ''
    else:
        try:
            element = float(element.replace(',','').replace('$',''))
        except ValueError:
            if '%' in element:
                try:
                    element = float(element.replace('%',''))/100
                except ValueError:
                    element = element
        return element

def extract(filepath,booleanTable=False,columnLabels=True,importanceTable=False):
    if importanceTable:
        vals = extractImportanceTable(filepath)
    elif booleanTable and columnLabels:
        vals = extractBoolean(filepath)
    elif booleanTable:
        vals = noLabelBoolean(filepath)
    else:
        with open(filepath) as f:
            reader = csv.reader(f)
            vals = {}
            for (i,line) in enumerate(reader):
                if not columnLabels:
                    vals.update({line[0]: list(map(cleanElement,line[1:]))})
                elif i == 0:
                    index = line
                else:
                    for (j,element) in enumerate(line):
                        if j == 0:
                            vals.update({element: {}})
                        else:
                            vals[line[0]].update({index[j]: cleanElement(element)})
    return vals

def extractBoolean(filepath):
    with open(filepath) as f:
        reader = csv.reader(f)
        for (i,line) in enumerate(reader):
            if i == 0:
                vals = {}
                index = line
            else:
                for (j,element) in enumerate(line):
                    if element.lower() == 'x':
                        vals.update({line[0]:index[j]})
    return vals

def noLabelBoolean(filepath):
    with open(filepath) as f:
        reader = csv.reader(f)
        vals = {}
        
        for line in reader:
            if line[1].lower() == 'x':
                vals.update({line[0]:'yes'})
            else:
                vals.update({line[0]:'no'})
    return vals

def extractImportanceTable(filepath):
    with open(filepath) as f:
        reader = csv.reader(f)
        vals = {}

        for line in reader:
            for (j,element) in enumerate(line):
                if element.lower() == 'x':
                    vals.update({line[0]:4-j})
    return vals
                    
#Chat GPT Generated
def dictJSON(dictionary, filename):
    with open(filename, 'w') as json_file:
        json.dump(dictionary, json_file, indent=4)
