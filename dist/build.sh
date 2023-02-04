#!/usr/bin/env bash

set -e
set -x

cd groovy && \
  conda env create -n beakerx_groovy --file configuration.yml && \
  . /opt/conda/etc/profile.d/conda.sh && \
  conda activate beakerx_groovy && \
  pip install -r requirements.txt --verbose && \
  beakerx_kernel_groovy install && \
  conda deactivate && \
  jupyter kernelspec install --sys-prefix --replace --name groovy /opt/conda/envs/beakerx_groovy/share/jupyter/kernels/groovy

cd ../base && \
  conda env create -n beakerx_base --file configuration.yml && \
  . /opt/conda/etc/profile.d/conda.sh && \
  conda activate beakerx_base && \
  pip install -r requirements.txt --verbose && \
  conda deactivate

cd ../tabledisplay && \
  conda env create -n beakerx_tabledisplay --file configuration.yml && \
  . /opt/conda/etc/profile.d/conda.sh && \
  conda activate beakerx_tabledisplay && \
  cd beakerx_tabledisplay &&
  pip install -r requirements.txt --verbose && \
  beakerx_tabledisplay install && \
  cd js && \
  jupyter labextension install @jupyter-widgets/jupyterlab-manager --no-build && \
  jupyter labextension install . --no-build && \
  conda deactivate

cd ../../../widgets && \
  conda env create -n beakerx_widgets --file configuration.yml && \
  . /opt/conda/etc/profile.d/conda.sh && \
  conda activate beakerx_widgets && \
  cd beakerx_widgets &&
  pip install -r requirements.txt --verbose && \
  cd js && \
  jupyter labextension install . --no-build && \
  conda deactivate

#jupyter labextension install @jupyter-widgets/jupyterlab-manager --no-build
#jupyter labextension install /opt/conda/envs/beakerx_tabledisplay --no-build
jupyter lab build