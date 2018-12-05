import pandas as pd
import matplotlib.pyplot as plt
import csv
import os



base_directory = "/home/pgarcia/NetBeansProjects/ECJ-NODE-GAINS/results"
files = []
problems = ["koza-2", "koza-3"]

"""
"koza-1",
"koza-2",
"koza-3",
"nguyen-1",
"nguyen-2",
"nguyen-3",
"nguyen-4",
"nguyen-5",
"nguyen-6",
"nguyen-7",
"nguyan-8",
"nguyen-9",
"nguyen-10",
"pagie-1",
"pagie-2",
"korns-1",
"korns-2",
"korns-3",
"korns-4",
"korns-5",
"korns-6",
"korns-7",
"korns-8",
"korns-9",
"korns-10",
"korns-11",
"korns-12",
"korns-13",
"korns-14",
"korns-15",
"keijzer-1",
"keijzer-2",
"keijzer-3",
"keijzer-4",
"keijzer-5",
"keijzer-6",
"keijzer-7",
"keijzer-8",
"keijzer-9",
"keijzer-10",
"keijzer-11",
"keijzer-12",
"keijzer-13",
"keijzer-14",
"keijzer-15",
"vladislavleva-1",
"vladislavleva-2",
"vladislavleva-3",
"vladislavleva-4",
"vladislavleva-5",
"vladislavleva-6",
"vladislavleva-7",
"vladislavleva-8",
"""



def obtain_df_from_dir(directory):
	df = pd.DataFrame(columns=('JOB','PROBLEM','TOTAL_TIME', 'EVAL_TIME','BEST','AVERAGE_END','NODES_BEST'))
	for p in problems:
		#print p
		for i in range(0,28):
			filename = directory+"/job."+str(i)+"."+p+".gens"
			#print("Opening file "+filename)
			with open(filename, 'rb') as f:
				reader = csv.reader(f, delimiter=" ")
				i = 0
				time = 0
				best = 0
				eval_time = 0
				average_best_gen = 0
				size_best = 0
				for row in reader:
					time = float(row[0]) + time
					eval_time = float(row[1]) + eval_time
					size_best = float(row[4])
					average_gen = float(row[5])
					best = float(row[7])
				df.loc[len(df)] = [i, p, time, eval_time, best, average_gen, size_best]
	return df

def print_results(dataframe):
	#print(dataframe)
	print(dataframe.groupby('PROBLEM').mean())

df_sa = obtain_df_from_dir(base_directory+"/sa")
df_nosa = obtain_df_from_dir(base_directory+"/without_sa")

print_results(df_sa)
print_results(df_nosa)


			