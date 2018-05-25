This is the git repository for the KHIOPS nodes in KNIME.

KhiopsDictionnary : Node to create a Dictionnary from a KNIME dataset.
Inports: KNIME dataset
Outports: 1 - Resulting dictionnary
		  2 - Path to the resulting dictionnary
Arguments: Path to the directory where the dictionnary should be created.

KhiopsDictionnaryFromTable : Node to create a Dictionnary from a table. Available types : Categorical, Numerical, Date
Inports: Table containing 2 columns, the name of the variable and the associated type.
Outports: Path to the resulting dictionnary
Arguments: The path to the directory where the dictionnary should be created.


KhiopsLearning : Node to execute the learning KHIOPS phase and creating the modeling.kdic file used in prediction.
Inports: -
Outports: Modeling path : Table containing path to the modeling.kdic file.
Arguments:  1 - Khiops File : Path to the Khiops exec file
		    2 - Dictionnary File : Path to the dictionnary file
		    3 - Training File : Path to the training file
		    4 - Variable to predict : Name of the variable to predict
		    5 - Separator : The separator used in the file to separate columns
		    6 - Result Directory : Dictionnary in which the result of the prediction will be created
		    

KhiopsPredictor : Node to predict a classification variable on an already trained model.
Inports: Modeling path : Table containing path to the modeling.kdic file.
Outports: -
Arguments:  1 - Khiops File : Path to the Khiops exec file
		    2 - Scoring File : Path to the scoring file
		    3 - Separator : The separator used in the file to separate columns
		    4 - Result Directory : Dictionnary in which the result of the prediction will be created
