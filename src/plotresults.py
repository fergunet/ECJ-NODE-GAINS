import pandas as pd
import matplotlib.pyplot as plt
import csv
import os
import re



base_directory = "/home/pgarcia/code/NODEGAINSCLUSTER"

files = []
problems = [
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
"nguyen-8",
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
]



def obtain_df_from_dir(directory):
	df = pd.DataFrame(columns=('JOB','PROBLEM','GENS','TOTAL_TIME','BEST','AVERAGE_END','NODES_BEST','VALIDATION_STANDARIZED', 'VALIDATION_ADJUSTED', 'VALIDATION_HITS', 'OPTIMAL_FOUND'))
	for p in problems:
		#print p
		for i in range(0,20):
			filename = directory+"/job."+str(i)+"."+p+".gens"
			filenameStats = directory+"/job."+str(i)+"."+p+".stats"
			#print("Opening file "+filename)
			with open(filename, 'rb') as f:
				reader = csv.reader(f, delimiter=" ")
				i = 0
				time = 0
				best = 0
				#eval_time = 0
				average_gen = 0
				gens = 0.0
				size_best = 0
				optimal_found = 0.0
				for row in reader:
					time = float(row[0])
					#eval_time = float(row[1]) + eval_time
					size_best = float(row[4])
					average_gen = float(row[5])
					best = float(row[7])
					if best == 1.0:
						#print "OPTIMAL FOUND IN "+filename
						optimal_found = 1.0
					gens = gens + 1

			with open(filenameStats, 'rb') as f:
				f.seek(-2, os.SEEK_END)  # Jump to the second last byte.
				while f.read(1) != b"\n":  # Until EOL is found...
					f.seek(-2, os.SEEK_CUR)  # ...jump back the read byte plus one more.
				last = f.readline()
				last_info = re.split(" |=|\n", last)
				#print(filenameStats)
				validation_standarized = float(last_info[2])
				validation_adjusted = float(last_info[4])
				validation_hits= float(last_info[6])
				#print(last_info)

			df.loc[len(df)] = [i, p, gens, time, best, average_gen, size_best, validation_standarized, validation_adjusted, validation_hits, optimal_found ]
	return df


def print_results(dataframe):
	pd.options.display.float_format = '{:20.10f}'.format
	#with pd.option_context('display.max_rows', None, 'display.max_columns', None):
	#print(dataframe.to_string())
	print(dataframe.groupby('PROBLEM').std().to_string())

df_nosa = obtain_df_from_dir(base_directory+"/NONE/classes")
df_sa = obtain_df_from_dir(base_directory+"/SA/classes")

print("NONE")
print_results(df_nosa)
print("SA")
print_results(df_sa)
