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
  jupyter labextension install @jupyter-widgets/jupyterlab-manager@3.0.1 --no-build && \
  jupyter labextension enable @jupyter-widgets/jupyterlab-manager && \
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

jupyter lab build

# TODO: fixing tabledisplay is not compatible with jupyterlab-manager@5.0.3
jupyter labextension disable @jupyter-widgets/jupyterlab-manager
pip uninstall -y jupyterlab_widgets
jupyter labextension install @jupyter-widgets/jupyterlab-manager@3.0.1
jupyter labextension enable @jupyter-widgets/jupyterlab-manager